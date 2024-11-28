import { createNativeStackNavigator } from '@react-navigation/native-stack'

import OnBoarding from '../pages/OnBoarding'
import Login from '../pages/Login'
import Home from '../pages/Home'
import Cadastro from '../pages/Cadastro'
import Agendamentos from '../pages/Agendamentos'
import Perfil from '../pages/Perfil'
import { Provider } from 'react-native-paper'
const Stack = createNativeStackNavigator();

export default function Routes() {
    return (
      <Provider>
        <Stack.Navigator>
            <Stack.Screen
                name="Perfil"
                component={Perfil}
                options={{headerShown: false}}
            />

            <Stack.Screen
                name="Login"
                component={Login}
                options={{headerShown: false}}
            />

            <Stack.Screen
                name="Home"
                component={Home}
                options={{headerShown: false}}
            />

            <Stack.Screen
                name="Cadastro"
                component={Cadastro}
                options={{headerShown: false}}
            />

            <Stack.Screen
                name="Agendamentos"
                component={Agendamentos}
                options={{headerShown: false}}
            />
            
        </Stack.Navigator>
        </Provider>
    );
}