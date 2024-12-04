import * as React from 'react';
import {Modal, Portal, Title, IconButton, TextInput} from 'react-native-paper';

export default function EditAgendamento({...props}) {
  const [visible, setVisible] = React.useState(false);

  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = {backgroundColor: 'white', padding: 20};

  return (
    <>
      <Portal>
        <Modal visible={visible} onDismiss={hideModal} contentContainerStyle={containerStyle}>
          <Title>Editar agendamento</Title>
          <TextInput label="Quadra" value={props.quadra} onChangeText={text => setText(text)} disabled/>

        </Modal>
      </Portal>
      <IconButton icon="folder-edit-outline" size={20} onPress={showModal} color={"#000000"}>>
      </IconButton>
    </>
  )
}
