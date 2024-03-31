window.addEventListener('load', function () {
    obtenerOdontologos();
    // Manejar formulario de agregar
    document.getElementById('formularioAgregar').addEventListener('submit', function (e) {
        e.preventDefault();
        const form = e.target
        agregarOdontologo(form);
    });

    //Manejar formulario de editar
    document.getElementById('formularioEditar').addEventListener('submit', function (e) {
        e.preventDefault();
        const form = e.target
        editarOdontologo(form);
    });

    document.getElementById('confirmarBorrar').addEventListener('click', function () {
        borrarOdontologo();
    });
});

function agregarOdontologo(form) {
    const formData = {
        nombre: document.querySelector('#alta_nombre').value,
        apellido: document.querySelector('#alta_apellido').value,
        matricula: document.querySelector('#alta_matricula').value,
    }
    //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
    //el odontólogo que enviaremos en formato JSON
    const url = '/odontologos';
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
                text: "Se agrego correctamente un odontologo",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "green",
                },
            }).showToast();
            form.reset();
            $('#modalAgregar').modal('hide');
            obtenerOdontologos();
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error agregando odontologo",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function editarOdontologo(form) {
    const formData = {
        id: document.querySelector('#editar_id').value,
        nombre: document.querySelector('#editar_nombre').value,
        apellido: document.querySelector('#editar_apellido').value,
        matricula: document.querySelector('#editar_matricula').value,
    }
    //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
    //el odontólogo que enviaremos en formato JSON
    const url = '/odontologos';
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
                text: "Se editó correctamente un odontologo",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "green",
                },
            }).showToast();
            form.reset();
            $('#modalEditar').modal('hide');
            obtenerOdontologos();
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error editando odontologo",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function obtenerOdontologos() {
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            cargarTablaOdontologos(data)
        })
        .catch((error) => {
            console.error(error)
            Toastify({
                text: "Error obteniendo odontologos",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function cargarTablaOdontologos(odontologos) {
    const tabla = document.getElementById('tablaOdontologos');
    // Selecciona el tbody dentro de la tabla específica por su id
    const cuerpoTabla = tabla.getElementsByTagName('tbody')[0];

    // Limpia el contenido del tbody, preservando el thead intacto
    if (cuerpoTabla) cuerpoTabla.innerHTML = '';
    odontologos.forEach((odontologo) => {
        let fila = cuerpoTabla.insertRow();
        fila.insertCell().textContent = odontologo.id;
        fila.insertCell().textContent = odontologo.nombre;
        fila.insertCell().textContent = odontologo.apellido;
        fila.insertCell().textContent = odontologo.matricula;
        let acciones = fila.insertCell();
        acciones.innerHTML = `
            <button class="btn btn-secondary btn-sm" onclick="abrirEditarOdontologo('${odontologo.id}')">Editar</button>
            <button class="btn btn-danger btn-sm" onclick="confirmacionBorrar('${odontologo.id}')">Borrar</button>
        `;
    });
}

function cargarModalEditar(odontologo) {
    document.querySelector('#editar_id').value = odontologo.id;
    document.querySelector('#editar_nombre').value = odontologo.nombre;
    document.querySelector('#editar_apellido').value = odontologo.apellido;
    document.querySelector('#editar_matricula').value = odontologo.matricula;
    $('#modalEditar').modal('show');
}

function abrirEditarOdontologo(id) {
    const url = `/odontologos/buscar/${id}`;
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
                text: "Error obteniendo odontologos",
                duration: 3000,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "red",
                },
            }).showToast();
        })
}

function borrarOdontologo() {
    const id = document.querySelector('#borrar_id').value;
    const url = `/odontologos/${id}`;
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
                obtenerOdontologos(); // Actualizar la lista después de eliminar uno
            } else {
                throw new Error('Error al eliminar el odontólogo');
            }
        })
        .catch(error => {
            console.error(error);
            Toastify({
                text: "Error al eliminar odontólogo",
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
