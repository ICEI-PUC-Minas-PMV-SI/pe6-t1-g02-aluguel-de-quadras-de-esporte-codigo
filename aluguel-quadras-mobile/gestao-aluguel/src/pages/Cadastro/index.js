import React, { useState } from "react";
import {
    View,
    Text,
    StyleSheet,
    TextInput,
    TouchableOpacity,
    ScrollView,
    KeyboardAvoidingView,
    Platform,
} from "react-native";
import { useNavigation } from "@react-navigation/native";
import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const schema = yup.object({
    email: yup.string().email("E-mail inválido").required("Informe seu e-mail"),
    password: yup
        .string()
        .min(6, "A senha deve conter pelo menos 6 caracteres")
        .required("Informe sua senha"),
    name: yup.string().required("Digite seu nome"),
    phone: yup.string()
        .max(14, 'O telefone deve conter 14 caracteres')
        .min(14, 'O telefone deve conter 14 caracteres (xx) xxxxxxxxx')
        .required('O número de telefone é obrigatório'),
    cpf: yup
        .string()
        .matches(/^\d{11}$/, "CPF deve conter 11 dígitos")
        .required("Digite seu CPF"),
});

export default function Cadastro() {
    const navigation = useNavigation();
    const {
        control,
        handleSubmit,
        formState: { errors },
    } = useForm({
        resolver: yupResolver(schema),
    });
    const [loading, setLoading] = useState(false);

    const handleSignUp = async (data) => {
        if (loading) return;

        setLoading(true);
        const signUpData = {
            email: data.email,
            nome: data.name,
            senha: data.password,
            telefone: data.phone,
            cpf: data.cpf,
            cnpj: "null"
        };

        try {
            console.log("Enviando dados:", JSON.stringify(signUpData));
            const response = await fetch("http://10.0.2.2:8080/api/v1/usuarios", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(signUpData),
            });

            const responseData = await response.json();
            console.log("Resposta do servidor:", JSON.stringify(responseData));
            if (response.ok) {
                alert("Sucesso! Cadastro realizado com sucesso!");
                navigation.navigate("Login");
            } else {
                alert("Erro", responseData.message || "Erro ao cadastrar usuário");

            }
        } catch (error) {
            console.error(error);
            alert("Erro! Não foi possível se conectar ao servidor.");
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
            {errors[name] && (
                <Text style={styles.errorText}>{errors[name]?.message}</Text>
            )}
        </View>
    );

    return (
        <KeyboardAvoidingView
            behavior={Platform.OS === "ios" ? "padding" : "height"}
            style={styles.container}
        >
            <ScrollView contentContainerStyle={styles.scrollContainer}>
                <View style={styles.headerContainer}>
                    <Text style={styles.headerText}>Faça seu cadastro</Text>
                </View>
                <View style={styles.formContainer}>
                    {renderInput("name", "Nome")}
                    {renderInput("phone", "Telefone")}
                    {renderInput("email", "E-mail")}
                    {renderInput("password", "Senha", true)}
                    {renderInput("cpf", "CPF")}

                    <TouchableOpacity
                        style={styles.button}
                        onPress={handleSubmit(handleSignUp)}
                        disabled={loading}
                    >
                        <Text style={styles.buttonText}>
                            {loading ? "Carregando..." : "Cadastrar"}
                        </Text>
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
        margin: 20,
        borderRadius: 15,
        padding: 20,
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
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
    errorText: {
        color: "#ff6b6b",
        fontSize: 14,
        marginTop: 5,
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
});

//pag