import React, { useEffect, useState } from 'react';
import {
    View,
    Text,
    StyleSheet,
    TextInput,
    TouchableOpacity,
    ScrollView,
    KeyboardAvoidingView,
    Platform,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { toastConfig } from '../../components/toast';
import Toast from 'react-native-toast-message';

const schema = yup.object({
    name: yup.string().required('Campo obrigatório'),
    phone: yup.string()

        .required('O número de telefone é obrigatório'),
    newpassword: yup.string().min(6, "A senha deve conter pelo menos 6 caracteres").required("Informe sua senha"),
    confirmnewpassword: yup.string()
        .oneOf([yup.ref('newpassword'), null], 'As senhas devem ser iguais')
        .required('A confirmação de senha é obrigatória')
});

export default function Perfil() {
    const navigation = useNavigation();
    const { control, handleSubmit, setValue, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const [loading, setLoading] = useState(false);
    const [userId, setUserId] = useState(null);

    const getProfileData = async () => {
        try {
            const id = await AsyncStorage.getItem('userId');
            if (id) {
                setUserId(id);
                const response = await fetch(`http://10.0.2.2:8080/api/v1/usuarios/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                });
                if (response.ok) {
                    const data = await response.json();
                    setValue('name', data.nome);
                    setValue('phone', data.telefone);
                } else {
                    console.error(response.status);
                    Toast.show({
                        type: 'customToast',
                        text1: 'Erro no perfil',
                        text2: 'Não foi possível carregar os dados do perfil',
                    });
                }
            }
        } catch (error) {
            console.error(error);
            Toast.show({
                type: 'customToast',
                text1: 'Erro no perfil',
                text2: 'Erro ao buscar os dados do usuário',
            });
        }
    };

    useEffect(() => {
        getProfileData();
    }, []);

    const handleUpdateProfile = async (data) => {
        setLoading(true);
        const profileData = {
            senha: data.newpassword,
            nome: data.name,
            telefone: data.phone,
        };

        try {
            const controller = new AbortController();
            const token = await AsyncStorage.getItem('token');

            // Configuração do timeout para 10 segundos
            const timeout = setTimeout(() => {
                controller.abort(); // Aborta a requisição
                Toast.show({
                    type: 'customToast',
                    text1: 'Tempo Excedido',
                    text2: 'A conexão demorou mais de 10 segundos. Tente novamente.',
                });
            }, 10000);

            const response = await fetch(`http://10.0.2.2:8080/api/v1/usuarios/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(profileData),
                signal: controller.signal,
            });

            clearTimeout(timeout);

            if (response.ok) {
                navigation.navigate('Home');
            } else {
                Toast.show({
                    type: 'customToast',
                    text1: 'Erro no perfil',
                    text2: 'Ocorreu um erro ao atualizar perfil',
                });
            }
        } catch (error) {
            Toast.show({
                type: 'customToast',
                text1: 'Erro no perfil',
                text2: 'Não foi possível se conectar ao servidor',
            });
        } finally {
            setLoading(false);
        }
    };

    const renderInput = (name, placeholder, secureTextEntry = false) => (
        <View style={styles.inputContainer}>
            <Text style={styles.label}>{placeholder}</Text>
            <Controller
                control={control}
                name={name}
                render={({ field: { onChange, onBlur, value } }) => (
                    <TextInput
                        placeholder={placeholder}
                        onChangeText={onChange}
                        onBlur={onBlur}
                        value={value}
                        style={styles.input}
                        secureTextEntry={secureTextEntry}
                        placeholderTextColor="#888"
                    />
                )}
            />
            {errors[name] && <Text style={styles.labelError}>{errors[name]?.message}</Text>}
        </View>
    );

    return (
        <KeyboardAvoidingView
            behavior={Platform.OS === "ios" ? "padding" : "height"}
            style={styles.container}
        >
            <ScrollView contentContainerStyle={styles.scrollContainer}>
                <View style={styles.headerContainer}>
                    <Text style={styles.headerText}>Atualize seu perfil</Text>
                </View>
                <View style={styles.formContainer}>
                    {renderInput("name", "Nome")}
                    {renderInput("phone", "Telefone")}
                    {renderInput("newpassword", "Nova senha", true)}
                    {renderInput("confirmnewpassword", "Confirmar nova senha", true)}

                    <TouchableOpacity
                        style={styles.button}
                        onPress={handleSubmit(handleUpdateProfile)}
                        disabled={loading}
                    >
                        <Text style={styles.buttonText}>
                            {loading ? "Carregando..." : "Atualizar"}
                        </Text>
                    </TouchableOpacity>
                    <Toast config={toastConfig} />
                </View>
            </ScrollView>
        </KeyboardAvoidingView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#121212",
    },
    scrollContainer: {
        flexGrow: 1,
    },
    headerContainer: {
        backgroundColor: "#1E1E1E",
        paddingVertical: 30,
        paddingHorizontal: 20,
        borderBottomLeftRadius: 30,
        borderBottomRightRadius: 30,
    },
    headerText: {
        fontSize: 28,
        fontWeight: "bold",
        color: "#FFFFFF",
        textAlign: "center",
    },
    formContainer: {
        flex: 1,
        backgroundColor: "#1E1E1E",
        borderTopLeftRadius: 25,
        borderTopRightRadius: 25,
        paddingHorizontal: 20,
        paddingTop: 30,
        marginTop: -25,
    },
    inputContainer: {
        marginBottom: 20,
    },
    label: {
        fontSize: 16,
        fontWeight: "600",
        color: "#E0E0E0",
        marginBottom: 5,
    },
    input: {
        borderWidth: 1,
        borderColor: "#333",
        borderRadius: 8,
        paddingHorizontal: 15,
        paddingVertical: 10,
        fontSize: 16,
        color: "#FFFFFF",
        backgroundColor: "#2C2C2C",
    },
    button: {
        backgroundColor: "#007BFF",
        borderRadius: 8,
        paddingVertical: 15,
        alignItems: "center",
        marginTop: 20,
    },
    buttonText: {
        color: "#FFFFFF",
        fontSize: 18,
        fontWeight: "bold",
    },
    labelError: {
        color: "#ff6b6b",
        fontSize: 14,
        marginTop: 5,
    },
});