import React from 'react';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity,
    Image,
    ScrollView
} from 'react-native';
import { useNavigation } from '@react-navigation/native';

export default function Home() {
    const navigation = useNavigation();


    return (
        <ScrollView style={styles.container}>
            <View style={styles.headerContainer}>
                <Text style={styles.headerText}>Bem-vindo!</Text>
            </View>

            <View style={styles.contentContainer}>
                {/* Conteúdo Informativo */}
                <View style={styles.infoSection}>
                    <Image
                        source={require('../../assets/esportes.png')}
                        style={styles.image}
                    />
                    <Text style={styles.infoText}>
                        Reserve quadras esportivas com facilidade e aproveite momentos incríveis com seus amigos!
                    </Text>
                    <Image
                        source={require('../../assets/tenis.png')}
                        style={styles.image}
                    />
                    <Text style={styles.infoText}>
                        Explore diversas opções de esportes como tênis, futevôlei e muito mais.
                    </Text>
                </View>

                {/* Botões de Navegação */}
                <View style={styles.buttonContainer}>
                    <TouchableOpacity
                        style={styles.button}
                        onPress={() => navigation.navigate("Agendamentos")}
                    >
                        <Text style={styles.buttonText}>Seus Agendamentos</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        style={styles.button}
                        onPress={() => navigation.navigate("Perfil")}
                    >
                        <Text style={styles.buttonText}>Perfil</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#121212',
    },
    headerContainer: {
        backgroundColor: '#1E1E1E',
        paddingVertical: 30,
        paddingHorizontal: 20,
        borderBottomLeftRadius: 30,
        borderBottomRightRadius: 30,
    },
    headerText: {
        fontSize: 28,
        fontWeight: 'bold',
        color: '#FFFFFF',
        textAlign: 'center',
    },
    contentContainer: {
        flex: 1,
        backgroundColor: '#1E1E1E',
        margin: 20,
        borderRadius: 15,
        padding: 20,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
    },
    infoSection: {
        marginTop: 16,
        alignItems: 'center',
    },
    infoText: {
        fontSize: 16,
        color: '#E0E0E0',
        textAlign: 'center',
        marginVertical: 16,
    },
    image: {
        width: '100%',
        height: 180,
        resizeMode: 'cover',
        borderRadius: 10,
        marginBottom: 16,
    },
    buttonContainer: {
        marginTop: 20,
        alignItems: 'center',
    },
    button: {
        backgroundColor: '#007BFF',
        width: '100%',
        borderRadius: 8,
        paddingVertical: 15,
        marginVertical: 8,
        justifyContent: 'center',
        alignItems: 'center',
    },
    buttonText: {
        fontSize: 18,
        color: '#FFFFFF',
        fontWeight: 'bold',
    },
});

