document.addEventListener('DOMContentLoaded', function () {
    // Agregar un evento de escucha al formulario de agregar turno
    document.getElementById('formularioAgregarTurno').addEventListener('submit', function (e) {
        e.preventDefault(); // Evita que el formulario se envíe

        // Obtener los valores de los campos del formulario
        const odontologoId = document.querySelector('#alta_odontologo').value;
        const pacienteId = document.querySelector('#alta_paciente').value;
        const fechaTurno = document.querySelector('#alta_fechaTurno').value;

        // Construir el objeto formData
        const formData = {
            odontologo: odontologoId,
            paciente: pacienteId,
            fechaTurno: fechaTurno
        };

        // URL del endpoint para guardar el turno
        const url = '/turnos/guardar';

        // Configurar la solicitud
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Enviar la solicitud
        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar turno');
                }
                // Limpiar el formulario
                document.getElementById('formularioAgregarTurno').reset();
                 // Ocultar el modal de turnos
                                $('#modalAgregar').modal('hide');
                // Mostrar un mensaje de éxito
                Toastify({
                    text: "Se agregó correctamente un turno",
                    duration: 3000,
                    gravity: "bottom",
                    position: "right",
                    style: {
                        background: "green",
                    },
                }).showToast();
                // Actualizar la lista de turnos
                obtenerTurnos();
            })
            .catch(error => {
                console.error(error);
                // Mostrar un mensaje de error
                Toastify({
                    text: "Error agregando turno",
                    duration: 3000,
                    gravity: "bottom",
                    position: "right",
                    style: {
                        background: "red",
                    },
                }).showToast();
            });
    });
});

function obtenerTurnos() {
    const url = '/turnos/listar';
    const settings = {
        method: 'GET'
    };

    // Enviar la solicitud GET para obtener la lista de turnos
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Llamar a la función mostrarTurnos para mostrar los turnos en la interfaz
            mostrarTurnos(data);
        })
        .catch((error) => {
            console.error(error);
            // Mostrar un mensaje de error
            Toastify({
                text: "Error obteniendo turnos",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        });
}

function mostrarTurnos(turnos) {
    const tablaTurnos = document.getElementById('tablaTurnos').getElementsByTagName('tbody')[0];

    // Limpiar la tabla antes de mostrar los turnos
    tablaTurnos.innerHTML = '';

    // Iterar sobre los turnos y agregarlos a la tabla
    turnos.forEach((turno) => {
        const fila = tablaTurnos.insertRow();
        fila.insertCell().textContent = turno.id;
        fila.insertCell().textContent = turno.odontologo.nombre + ' ' + turno.odontologo.apellido;
        fila.insertCell().textContent = turno.paciente.nombre + ' ' + turno.paciente.apellido;
        fila.insertCell().textContent = turno.fechaTurno;
    });
}

// Llamar a obtenerTurnos al cargar la página para mostrar la lista de turnos
obtenerTurnos();