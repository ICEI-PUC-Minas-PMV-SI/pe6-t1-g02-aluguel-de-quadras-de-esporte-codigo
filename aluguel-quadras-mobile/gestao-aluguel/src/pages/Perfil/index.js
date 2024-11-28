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
import { MaskedTextInput } from 'react-native-mask-text';

import AsyncStorage from '@react-native-async-storage/async-storage';

const schema = yup.object({
    name: yup.string().required('Campo obrigatório'),
    phone: yup.string()
        .min(15, 'O telefone deve conter 11 números')
        .required('O número de telefone é obrigatório'),
    email: yup.string().email("E-mail inválido").required("Informe seu e-mail"),
    newpassword: yup.string().min(6, "A senha deve conter pelo menos 6 caracteres").required("Informe sua senha"),
    confirmnewpassword: yup.string()
        .oneOf([yup.ref('newpassword'), null], 'As senhas devem ser iguais')
        .required('A confirmação de senha é obrigatória')
});

export default function Login() {

    const navigation = useNavigation();
    const { control, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const [loading, setLoading] = useState(false);

    const handleLogin = async (data) => {
        setLoading(true);
        const loginData = {
            email: data.email,
            senha: data.password
        };

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
                await AsyncStorage.setItem('userToken', result.token);
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
                        <MaskedTextInput
                            mask="(99) 99999-9999"
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
                    onPress={handleSubmit(handleLogin)}
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
