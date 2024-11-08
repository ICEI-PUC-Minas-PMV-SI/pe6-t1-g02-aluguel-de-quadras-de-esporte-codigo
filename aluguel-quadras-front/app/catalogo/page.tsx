import { AuthProvider } from '../shared/auth/auth-context';
import QuadraCatalogo from './QuadraCatalogo';

export default function CatalogoPage() {
  return (
    <AuthProvider>
  <QuadraCatalogo />
    </AuthProvider>

  
);
}
