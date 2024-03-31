window.addEventListener('load', function () {
    obtenerPacientes();
    // Manejar formulario de agregar
    document.getElementById('formularioAgregar').addEventListener('submit', function (e) {
        e.preventDefault();
        const form = e.target
        agregarPaciente(form);
    });

    //Manejar formulario de editar
    document.getElementById('formularioEditar').addEventListener('submit', function (e) {
        e.preventDefault();
        const form = e.target
        editarPaciente(form);
    });

    document.getElementById('confirmarBorrar').addEventListener('click', function () {
        borrarPaciente();
    });
});

function agregarPaciente(form) {
    const formData = {
        nombre: document.querySelector('#alta_nombre').value,
        apellido: document.querySelector('#alta_apellido').value,
        dni: document.querySelector('#alta_dni').value,
        fechaAltaPrivat: new Date(),
        domicilio: {
            calle: document.querySelector('#alta_calle').value,
            numero: document.querySelector('#alta_numero').value,
            localidad: document.querySelector('#alta_localidad').value,
            provincia: document.querySelector('#alta_provincia').value,
        }
    }

    //invocamos utilizando la función fetch la API pacinete con el método POST que guardará
    //el paciente que enviaremos en formato JSON
    const url = '/pacientes/guardar';
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(() => {
            Toastify({
                text: "Se agrego correctamente un paciente",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "green",
                },
            }).showToast();
            form.reset();
            $('#modalAgregar').modal('hide');
            obtenerPacientes();
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error agregando paciente",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function editarPaciente(form) {
    const formData = {
        id: document.querySelector('#editar_id').value,
        nombre: document.querySelector('#editar_nombre').value,
        apellido: document.querySelector('#editar_apellido').value,
        dni: document.querySelector('#editar_dni').value,
        fechaAltaPrivat: document.querySelector('#editar_fechaAltaPrivat').value,
        domicilio: {
            id: document.querySelector('#editar_domicilio_id').value,
            calle: document.querySelector('#editar_calle').value,
            numero: document.querySelector('#editar_numero').value,
            localidad: document.querySelector('#editar_localidad').value,
            provincia: document.querySelector('#editar_provincia').value,
        }
    }
    //invocamos utilizando la función fetch la API paciente con el método POST que guardará
    //el paciente que enviaremos en formato JSON
    const url = '/pacientes/actualizar';
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(() => {
            Toastify({
                text: "Se editó correctamente un paciente",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "green",
                },
            }).showToast();
            form.reset();
            $('#modalEditar').modal('hide');
            obtenerPacientes();
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error editando paciente",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function obtenerPacientes() {
    const url = '/pacientes/listar';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            cargarTablaPacientes(data)
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error obteniendo pacientes",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function cargarTablaPacientes(pacientes) {
    const tabla = document.getElementById('tablaPacientes');
    // Selecciona el tbody dentro de la tabla específica por su id
    const cuerpoTabla = tabla.getElementsByTagName('tbody')[0];

    // Limpia el contenido del tbody, preservando el thead intacto
    if (cuerpoTabla) cuerpoTabla.innerHTML = '';
    pacientes.forEach((paciente) => {
        let fila = cuerpoTabla.insertRow();
        fila.insertCell().textContent = paciente.id;
        fila.insertCell().textContent = paciente.nombre;
        fila.insertCell().textContent = paciente.apellido;
        fila.insertCell().textContent = paciente.dni;
        fila.insertCell().textContent = paciente.fechaAltaPrivat;
        fila.insertCell().textContent = paciente.domicilio.calle;
        fila.insertCell().textContent = paciente.domicilio.numero;
        fila.insertCell().textContent = paciente.domicilio.localidad;
        fila.insertCell().textContent = paciente.domicilio.provincia;


        let acciones = fila.insertCell();
        acciones.innerHTML = `
            <button class="btn btn-secondary btn-sm" onclick="abrirEditarPaciente('${paciente.id}')">Editar</button>
            <button class="btn btn-danger btn-sm" onclick="confirmacionBorrar('${paciente.id}')">Borrar</button>
        `;
    });
}

function cargarModalEditar(paciente) {
    document.querySelector('#editar_id').value = paciente.id;
    document.querySelector('#editar_nombre').value = paciente.nombre;
    document.querySelector('#editar_apellido').value = paciente.apellido;
    document.querySelector('#editar_dni').value = paciente.dni;
    document.querySelector('#editar_fechaAltaPrivat').value = paciente.fechaAltaPrivat;
    document.querySelector('#editar_domicilio_id').value = paciente.domicilio.id;
    document.querySelector('#editar_calle').value = paciente.domicilio.calle;
    document.querySelector('#editar_numero').value = paciente.domicilio.numero;
    document.querySelector('#editar_localidad').value = paciente.domicilio.localidad;
    document.querySelector('#editar_provincia').value = paciente.domicilio.provincia;
    $('#modalEditar').modal('show');
}

function abrirEditarPaciente(id) {
    const url = `/pacientes/buscar/${id}`;
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            cargarModalEditar(data)
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error obteniendo pacientes",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function borrarPaciente() {
    const id = document.querySelector('#borrar_id').value;
    const url = `/pacientes/eliminar?id=${id}`;
    const settings = {
        method: 'DELETE'
    }

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                Toastify({
                    text: "Odontólogo eliminado correctamente",
                    duration: 3000,
                    gravity: "bottom",
                    position: "right",
                    style: {
                        background: "green",
                    },
                }).showToast();
                $('#modalConfirmacionBorrar').modal('hide');
                obtenerPacientes(); // Actualizar la lista después de eliminar uno
            } else {
                throw new Error('Error al eliminar el paciente');
            }
        })
        .catch(error => {
            console.error(error);
            Toastify({
                text: "Error al eliminar paciente",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        });
}

function confirmacionBorrar(id) {
    document.querySelector('#borrar_id').value = id;
    $('#modalConfirmacionBorrar').modal('show');
}
