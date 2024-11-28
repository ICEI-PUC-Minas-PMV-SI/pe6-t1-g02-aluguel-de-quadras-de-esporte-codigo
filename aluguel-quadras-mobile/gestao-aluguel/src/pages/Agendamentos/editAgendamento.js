import * as React from 'react';
import {View, Text} from "react-native";
import {Modal, Portal, Title, IconButton, TextInput, Button} from 'react-native-paper';
import {DatePickerModal, TimePickerModal} from 'react-native-paper-dates';
import {SafeAreaProvider} from "react-native-safe-area-context";
import TimePickerBr from "./timePicker";

export default function EditAgendamento({...props}) {
  const [visibleSingle, setVisibleSingle] = React.useState(false);
  const [open, setOpen] = React.useState(false);
  const [date, setDate] = React.useState(undefined);
  const [horaInicio, setHoraInicio] = React.useState(undefined);
  const [horaFim, setHoraFim] = React.useState(undefined);

  const showModal = () => setVisibleSingle(true);
  const hideModal = () => setVisibleSingle(false);
  const containerStyle = {backgroundColor: 'white', padding: 20};

  const onDismissSingle = React.useCallback(() => {
    setOpen(false);
  }, [setOpen]);

  const onConfirmSingle = React.useCallback(
    (params) => {
      setOpen(false);
      setDate(params.date);
    },
    [setOpen, setDate]
  );

  return (
    <>
      <Portal>
        <Modal visible={visibleSingle} onDismiss={hideModal} contentContainerStyle={containerStyle}>
          <Title>Editar agendamento</Title>
          <TextInput label="Quadra" value={props.quadra} onChangeText={text => setText(text)} disabled/>

          <SafeAreaProvider>
            <View style={{justifyContent: 'center', flex: 1, alignItems: 'center'}}>
              <Button onPress={() => setOpen(true)} uppercase={false} mode="outlined">
                {date ? date.toLocaleDateString("pt-BR") : `Selecione uma data`}
              </Button>
              <DatePickerModal
                locale="pt"
                mode="single"
                visible={open}
                onDismiss={onDismissSingle}
                date={date}
                onConfirm={onConfirmSingle}
              />
            </View>
          </SafeAreaProvider>
          <TimePickerBr defaultMessage="Inicio agendamento" confirmFunction={(a, b) => {
            setHoraInicio(`${a}:${b}`)
          }}/>
          <TimePickerBr defaultMessage="Fim agendamento" confirmFunction={(a, b) => {
            setHoraFim(`${a}:${b}`)
          }}/>
        </Modal>
      </Portal>
      <IconButton icon="folder-edit-outline" size={20} onPress={showModal} color={"#000000"}>>
      </IconButton>
    </>
  )
}