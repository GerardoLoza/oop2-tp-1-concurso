package modelo;

import api.NotificacionService;

public class FakeEnvioDeMail implements NotificacionService {
    private String mensajeEnviado;

    @Override
    public void enviarNotificacion(String mensaje, String destinatario) {
        this.mensajeEnviado = mensaje;
    }

    public String obtenerMensajeEnviado() {
        return mensajeEnviado;
    }
}
