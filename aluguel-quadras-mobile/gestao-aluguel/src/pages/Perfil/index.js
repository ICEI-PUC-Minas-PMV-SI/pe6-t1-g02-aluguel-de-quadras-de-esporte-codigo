import React, { useEffect, useState } from 'react';
import {
    View,
    Text,
    StyleSheet,
    TextInput,
    TouchableOpacity
} from 'react-native';

import { useNavigation } from '@react-navigation/native';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { MaskedTextInput } from 'react-native-mask-text';

import AsyncStorage from '@react-native-async-storage/async-storage';

const schema = yup.object({
    name: yup.string().required('Campo obrigatório'),
    phone: yup.string()
        .max(14, 'O telefone deve conter 14 caracteres')
        .min(14, 'O telefone deve conter 14 caracteres (xx) xxxxxxxxx')
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
        console.log("chamou a função getProfileData");
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
                    const data = await response.json().catch(err => console.error("Erro ao fazer parse do JSON:", err));
                    setValue('name', data.nome);
                    setValue('phone', data.telefone);
                } else {
                    console.error(response.status);
                    alert('Erro! Não foi possível carregar os dados do perfil.');
                }
            }
        } catch (error) {
            console.error(error);
            alert('Erro ao buscar os dados do usuário.');
        }
    };

    useEffect(() => {
        getProfileData();
    }, []);

    const handleUpdateProfile = async (data) => {
        console.log("chamou a funcao update profile");
        const profileData = {
            senha: data.newpassword,
            nome: data.name,
            telefone: data.phone,
        };

        console.log("Dados enviados:", profileData);

        try {
            // Usando AbortController para limitar o tempo da requisição
            const controller = new AbortController();
            const timeout = setTimeout(() => controller.abort(), 10000); // 10 segundos de timeout
            const token = await AsyncStorage.getItem('token');

            const response = await fetch(`http://10.0.2.2:8080/api/v1/usuarios/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(profileData),
                signal: controller.signal, // Passando o signal para a requisição
            });

            clearTimeout(timeout); // Limpar o timeout após a resposta

            if (response.ok) {
                alert('Sucesso! Perfil atualizado com sucesso!');
                console.log('Navegando para Home');
                navigation.navigate('Home');
            } else {
                alert('Ocorreu um erro ao atualizar perfil');
            }
        } catch (error) {
            console.error(error);
            alert('Erro! Não foi possível se conectar ao servidor.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <View style={styles.container}>

            <View style={styles.containerHeader}>
                <Text style={styles.message}>Atualize seu perfil</Text>
            </View>

            <View style={styles.containerForm}>

                <Text style={styles.title}>Nome</Text>
                <Controller
                    control={control}
                    name="name"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                        />
                    )}
                />
                {errors.name && <Text style={styles.labelError}>{errors.name?.message}</Text>}

                <Text style={styles.title}>Telefone</Text>
                <Controller
                    control={control}
                    name="phone"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                            keyboardType="numeric"
                        />
                    )}
                />
                {errors.phone && <Text style={styles.labelError}>{errors.phone?.message}</Text>}

                <Text style={styles.title}>Nova senha</Text>
                <Controller
                    control={control}
                    name="newpassword"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            placeholder="Digite sua nova senha"
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                            secureTextEntry
                        />
                    )}
                />
                {errors.newpassword && <Text style={styles.labelError}>{errors.newpassword?.message}</Text>}

                <Text style={styles.title}>Confirmar nova senha</Text>
                <Controller
                    control={control}
                    name="confirmnewpassword"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            placeholder="Confirme sua nova senha"
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                            secureTextEntry
                        />
                    )}
                />
                {errors.confirmnewpassword && <Text style={styles.labelError}>{errors.confirmnewpassword?.message}</Text>}

                <TouchableOpacity
                    style={styles.buttonCadastrar}
                    onPress={handleSubmit(handleUpdateProfile)}
                    disabled={loading}
                >
                    <Text style={styles.buttonTextCadastrar}>{loading ? 'Carregando...' : 'Atualizar'}</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#FFF',
        flex: 1,
    },
    containerHeader: {
        marginBottom: '1%',
        alignSelf: 'center',
        alignContent: 'center',
    },
    message: {
        fontSize: 24,
        fontWeight: 'bold',
        color: '#000'
    },
    containerForm: {
        flex: 1,
        backgroundColor: '#000',
        borderTopLeftRadius: 25,
        borderTopRightRadius: 25,
        paddingStart: '5%',
        paddingEnd: '5%'
    },
    title: {
        fontSize: 16,
        marginTop: 20,
        color: '#FFF'
    },
    input: {
        borderBottomWidth: 1,
        backgroundColor: '#FFF',
        color: '#000',
        marginTop: 12,
        borderRadius: 8,
        height: 48,
        fontSize: 16
    },
    buttonCadastrar: {
        backgroundColor: '#007BFF',
        width: '100%',
        borderRadius: 8,
        paddingVertical: 8,
        marginTop: 48,
        justifyContent: 'center',
        alignItems: 'center'
    },
    buttonTextCadastrar: {
        color: '#FFFFFF',
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center'
    },
    labelError: {
        alignSelf: 'flex-start',
        color: '#ff375b'
    }
});
