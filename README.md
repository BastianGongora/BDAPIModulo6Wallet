# Proyecto Alke Wallet
### Descripción
Alke Wallet es una billetera virtual que permite a los usuarios realizar un seguimiento de sus tarjetas y gastos, así como efectuar pagos virtuales. El proyecto se encuentra en desarrollo y sigue el patrón de arquitectura MVVM (Model-View-ViewModel).

### Funcionalidades Implementadas
Vista de Inicio

Saludo personalizado.
Visualización del balance total del usuario.
Botones para enviar e ingresar dinero.
Listado de las últimas transacciones realizadas.
Lista de Transacciones

Visualización de transacciones con imagen del usuario, nombre, fecha y cantidad transferida.
Arquitectura del Proyecto
El proyecto utiliza MVVM para separar la lógica de presentación, negocio y datos, facilitando así el mantenimiento y escalabilidad del código.

### Tecnologías Utilizadas
- Retrofit: Para la comunicación con la API REST.
- Room: Para el almacenamiento local de datos.
- Picasso: Para la carga y presentación de imágenes.
- LiveData y ViewModel: Para gestionar y observar los cambios en los datos de manera eficiente.
- Resumen del Proceso
- Vista (View): Fragmentos y actividades que muestran la interfaz de usuario.
- ViewModel: Gestiona la lógica de negocio y proporciona los datos necesarios a la vista.
- Repositorio (Repository): Obtiene y manipula los datos necesarios, interactuando con Retrofit y Room.
### Pruebas Implementadas
- Pruebas Unitarias: Para validar la lógica de negocio y la correcta interacción entre componentes.
- Pruebas de Integración: Para asegurar que la aplicación funcione correctamente en su conjunto, incluyendo la comunicación con la API y el almacenamiento local.
## Conclusión
Alke Wallet está diseñado para ofrecer una experiencia de usuario fluida y segura, permitiendo un manejo eficiente de las finanzas personales a través de una interfaz intuitiva y robusta.
