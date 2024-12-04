import React, { useState } from 'react';
import {
    View,
    Text,
    StyleSheet,
    TextInput,
    TouchableOpacity,
    KeyboardAvoidingView,
    Platform,
    ScrollView,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import AsyncStorage from '@react-native-async-storage/async-storage';

const schema = yup.object({
    email: yup.string().email("E-mail inválido").required("Informe seu e-mail"),
    password: yup.string().min(6, "A senha deve conter pelo menos 6 caracteres").required("Informe sua senha")
})

export default function Login() {
    const navigation = useNavigation();
    const { control, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    })
    const [loading, setLoading] = useState(false);

    const handleLogin = async (data) => {
        setLoading(true);
        const loginData = {
            email: data.email,
            senha: data.password
        }

        try {
            const response = await fetch('http://10.0.2.2:8080/api/v1/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                const result = await response.json();
                await AsyncStorage.setItem('userId', result.user.id);
                await AsyncStorage.setItem('token', result.token);
                console.log("navegando para o perfil");
                navigation.navigate('Home');
            } else {
                alert('Credenciais inválidas');
            }
        } catch (error) {
            console.error(error);
            alert('Erro login! Não foi possível se conectar ao servidor.');
        } finally {
            setLoading(false);
        }
    }

    const renderInput = (name, placeholder, secureTextEntry = false) => (
        <View style={styles.inputContainer}>
            <Text style={styles.title}>{placeholder}</Text>
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
    )

    return (
        <KeyboardAvoidingView
            behavior={Platform.OS === "ios" ? "padding" : "height"}
            style={styles.container}
        >
            <ScrollView contentContainerStyle={styles.scrollContainer}>
                <View style={styles.containerHeader}>
                    <Text style={styles.message}>Bem-vindo(a)</Text>
                </View>

                <View style={styles.containerForm}>
                    {renderInput("email", "E-mail")}
                    {renderInput("password", "Senha", true)}

                    <TouchableOpacity
                        style={styles.buttonAcessar}
                        onPress={handleSubmit(handleLogin)}
                        disabled={loading}
                    >
                        <Text style={styles.buttonTextAcessar}>{loading ? 'Carregando...' : 'Acessar'}</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        style={styles.buttonCadastro}
                        onPress={() => navigation.navigate("Cadastro")}
                    >
                        <Text style={styles.buttonTextCadastrar}>Cadastrar</Text>
                    </TouchableOpacity>
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
    containerHeader: {
        backgroundColor: "#1E1E1E",
        paddingVertical: 30,
        paddingHorizontal: 20,
        borderBottomLeftRadius: 30,
        borderBottomRightRadius: 30,
    },
    message: {
        fontSize: 28,
        fontWeight: "bold",
        color: "#FFFFFF",
        textAlign: "center",
    },
    containerForm: {
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
    title: {
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
    buttonAcessar: {
        backgroundColor: "#007BFF",
        width: "100%",
        borderRadius: 8,
        paddingVertical: 15,
        marginTop: 20,
        justifyContent: "center",
        alignItems: "center",
    },
    buttonTextAcessar: {
        color: "#FFFFFF",
        fontSize: 18,
        fontWeight: "bold",
        textAlign: "center"
    },
    buttonCadastro: {
        backgroundColor: "#2C2C2C",
        width: "100%",
        borderRadius: 8,
        paddingVertical: 15,
        marginTop: 15,
        justifyContent: "center",
        alignItems: "center",
    },
    buttonTextCadastrar: {
        fontSize: 18,
        color: "#007BFF",
        textAlign: "center",
        fontWeight: "bold"
    },
    labelError: {
        color: "#ff6b6b",
        fontSize: 14,
        marginTop: 5,
    }
});

