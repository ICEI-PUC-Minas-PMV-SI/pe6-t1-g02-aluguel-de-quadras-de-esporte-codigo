import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    TextInput,
    TouchableOpacity
} from 'react-native';

export default function Login() {
    return (
        <View style={styles.container}>

            <View style={styles.containerHeader}>
                <Text style={styles.message}>Bem-vindo(a)</Text>
            </View>

            <View style={styles.containerForm}>

                <Text style={styles.title}>E-mail</Text>
                <TextInput
                    placeholder="Digite seu e-mail"
                    style={styles.input}
                />

                <Text style={styles.title}>Senha</Text>
                <TextInput
                    placeholder="Digite sua senha"
                    style={styles.input}
                />

                <TouchableOpacity style={styles.buttonAcessar}>
                    <Text style={styles.buttonTextAcessar}>Acessar</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.buttonCadastro}>
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
        marginBottom:'8%',
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
        color:'#FFFFFF',
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center'
    },
    buttonCadastro : {
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
})