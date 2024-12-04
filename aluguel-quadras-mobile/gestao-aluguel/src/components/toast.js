import React from 'react';
import Toast, { BaseToast } from 'react-native-toast-message';
import { StyleSheet } from 'react-native';

export const toastConfig = {
    customToast: ({ text1, text2, ...rest }) => (
        <BaseToast
            {...rest}
            style={styles.toastContainer}
            text1Style={styles.text1}
            text2Style={styles.text2}
            text1={text1}
            text2={text2}
        />
    ),
};

const styles = StyleSheet.create({
    toastContainer: {
        borderLeftColor: '#ff6b6b', // Borda lateral vermelha
        backgroundColor: '#FFF', // Cor de fundo do Toast
    },
    text1: {
        fontSize: 16, // Aumenta o tamanho do título
        fontWeight: 'bold',
        color: '#000',
    },
    text2: {
        fontSize: 14, // Aumenta o tamanho do subtítulo
        color: '#000',
    },
});
