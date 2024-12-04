import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity
} from 'react-native';


import { useNavigation } from '@react-navigation/native';


export default function Home() {

 const navigation = useNavigation();

    return (
        <View style={styles.containerForm}>

            <View style={styles.containerHeader}>
                <Text style={styles.message}>Bem-vindo, nome</Text>

            </View>

            <View style={styles.containerForm}>

                <TouchableOpacity
                style={styles.buttonMenu}
                onPress={() => navigation.navigate("Agendamentos")}
                >
                    <Text style={styles.buttonText}>Seus agendamentos</Text>
                </TouchableOpacity>

                <TouchableOpacity
                style={styles.buttonMenu}
                onPress={() => navigation.navigate("Perfil")}
                >
                    <Text style={styles.buttonText}>Perfil</Text>
                </TouchableOpacity>

            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    containerHeader: {
        marginTop: '14%',
        marginBottom:'8%',
        paddingStart: '5%'
    },
    message: {
        fontSize: 28,
        fontWeight: 'bold',
        color: '#FFF'
    },
    containerForm: {
        flex: 1,
        backgroundColor: '#000',
        borderTopLeftRadius: 25,
        borderTopRightRadius: 25,
        paddingStart: '5%',
        paddingEnd: '5%'
    },

    buttonMenu: {
        backgroundColor: '#FFF',
        width: '100%',
        borderRadius: 8,
        paddingVertical: 8,
        marginTop: 24,
        justifyContent: 'center',
        alignItems: 'center'
    },
    buttonText: {
        fontSize: 18,
        color: '#000',
        textAlign: 'center',
        fontWeight: 'bold'
    },
})