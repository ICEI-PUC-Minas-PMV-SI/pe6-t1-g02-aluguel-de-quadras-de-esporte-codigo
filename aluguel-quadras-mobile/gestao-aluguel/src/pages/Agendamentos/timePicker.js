import * as React from 'react';
import {View, Text} from "react-native";
import {Button} from 'react-native-paper';
import {TimePickerModal} from 'react-native-paper-dates';
import {SafeAreaProvider} from "react-native-safe-area-context";

export default function TimePickerBr({...props}) {
  const [visible, setVisible] = React.useState(false)
  const [hours, setHours] = React.useState(undefined)
  const [minutes, setMinutes] = React.useState(undefined)

  const onDismiss = React.useCallback(() => {
    setVisible(false)
  }, [setVisible])

  const onConfirm = React.useCallback(
    ({hours, minutes}) => {
      setHours(hours)
      setMinutes(minutes)
      setVisible(false);
      props.confirmFunction(hours, minutes);
    },
    [setVisible]
  );

  return (
    <SafeAreaProvider>
      <View style={{display: 'flex', justifyContent: 'center', alignItems: 'center', marginTop: 16}}>
        <Button onPress={() => setVisible(true)} uppercase={false} mode="outlined">
          {hours || minutes ? `${hours}:${minutes}` : props.defaultMessage}
        </Button>
        <TimePickerModal
          locale="pt"
          visible={visible}
          onDismiss={onDismiss}
          onConfirm={onConfirm}
          hours={0}
          minutes={0}
        />
      </View>
    </SafeAreaProvider>
  )
}