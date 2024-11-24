import React, { useState } from 'react';
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
                await AsyncStorage.setItem('userToken', result.token); // Salva o token
                navigation.navigate('Home');
            } else {
                alert('Credenciais inválidas');
            }
        } catch (error) {
            console.error(error);
            alert('Erro! Não foi possível se conectar ao servidor.');
        } finally {
            setLoading(false);
        }
    }

    return (
        <View style={styles.container}>

            <View style={styles.containerHeader}>
                <Text style={styles.message}>Bem-vindo(a)</Text>
            </View>

            <View style={styles.containerForm}>

                <Text style={styles.title}>E-mail</Text>

                <Controller
                    control={control}
                    name="email"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            placeholder="Digite seu e-mail"
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                        />
                    )}
                />
                {errors.email && <Text style={styles.labelError}>{errors.email?.message}</Text>}


                <Controller
                    control={control}
                    name="password"
                    render={({ field: { onChange, onBlur, value } }) => (
                        <TextInput
                            placeholder="Digite sua senha"
                            onChangeText={onChange}
                            onBlur={onBlur}
                            value={value}
                            style={styles.input}
                            secureTextEntry={true}
                        />
                    )}
                />
                {errors.password && <Text style={styles.labelError}>{errors.password?.message}</Text>}

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
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#FFF',
        flex: 1,
    },
    containerHeader: {
        marginTop: '14%',
        marginBottom: '8%',
        paddingStart: '5%'
    },
    message: {
        fontSize: 28,
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
        fontSize: 20,
        marginTop: 28,
        color: '#FFF'
    },
    input: {
        borderBottomWidth: 1,
        backgroundColor: '#FFF',
        color: '#000',
        marginTop: 12,
        borderRadius: 8,
        height: 56,
        fontSize: 16
    },
    buttonAcessar: {
        backgroundColor: '#FF5722',
        width: '100%',
        borderRadius: 8,
        paddingVertical: 8,
        marginTop: 48,
        justifyContent: 'center',
        alignItems: 'center'
    },
    buttonTextAcessar: {
        color: '#FFFFFF',
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center'
    },
    buttonCadastro: {
        backgroundColor: '#FFF',
        width: '100%',
        borderRadius: 8,
        paddingVertical: 8,
        marginTop: 24,
        justifyContent: 'center',
        alignItems: 'center'
    },
    buttonTextCadastrar: {
        fontSize: 18,
        color: '#000',
        textAlign: 'center',
        fontWeight: 'bold'
    },
    labelError: {
        alignSelf: 'flex-start',
        color: '#ff375b',
        marginBottom: 8,
    }
})