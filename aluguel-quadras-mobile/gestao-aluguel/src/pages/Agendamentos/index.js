import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity,
} from 'react-native';
import EditAgendamento from './editAgendamento'
import {
    List,
    IconButton,
    Caption
} from 'react-native-paper';

import {useNavigation} from '@react-navigation/native';
import {useState} from 'react'

export default function Agendamentos(props) {

    const navigation = useNavigation();
    const [agendamentos, setAgendamentos] = useState([
        {
            id: 1,
            nome: "Quadra 1",
            status: 1,
            local: "Local A",
            data: "27/07/2024",
            horaInicio: "20h00",
            horaFim: "22h00"
        },
        {
            id: 2,
            nome: "Quadra 2",
            status: 0,
            local: "Local B",
            data: "27/07/2024",
            horaInicio: "16h00",
            horaFim: "19h00"
        },
    ])

    function cancelarAgendamento(id) {
        const copyAgendamentos = [...agendamentos]
        for (let i = 0; i < copyAgendamentos.length; i++) {
            if (copyAgendamentos[i].id === id) {
                copyAgendamentos[i].status = 0
                break
            }
        }

        setAgendamentos(copyAgendamentos)
    }

    return (
        <View style={styles.containerForm}>
            <IconButton icon={"arrow-left"} color={"#000000"} onPress={() => {
                navigation.navigate("Home")
            }}/>
            <View style={styles.containerHeader}>
                <Text style={styles.message}>Seus agendamentos</Text>

            </View>

            <View style={styles.containerForm}>
                {
                    agendamentos.map((agendamento) =>
                        <List.Item
                            key = {agendamento.id}
                            title={`${agendamento.nome} - ${agendamento.data}`}
                            description={`${agendamento.horaInicio} - ${agendamento.horaFim} ${agendamento.local}`}
                            left={props => <List.Icon {...props} color={"#000000"} icon="alarm"/>}
                            right={props => agendamento.status === 1 ? <>

                                    <EditAgendamento quadra={agendamento.nome}/>
                                    <IconButton icon="delete" size={20} onPress={() => {
                                        cancelarAgendamento(agendamento.id)
                                    }} color={"#FF6961"}/>


                                </>
                                :
                                <Text style={styles.chipContainer}>
                                    <Caption style={styles.chip}>
                                        Cancelado
                                    </Caption>
                                </Text>
                            }
                        />
                    )
                }


            </View>
        </View>
    );
}

const styles = StyleSheet.create({
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
        backgroundColor: '#FFF',
        borderTopLeftRadius: 25,
        borderTopRightRadius: 25,
        paddingStart: '5%',
        paddingEnd: '5%'
    },
    chip: {
        alignSelf: "center",
        backgroundColor: `#000`,
        color: `#fff`,
        padding: 4,
        borderRadius: 30
    },
    chipContainer: {
        display: "flex"
    }
})