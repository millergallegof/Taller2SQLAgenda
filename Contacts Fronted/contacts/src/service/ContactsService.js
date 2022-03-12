import axios from 'axios';

// Clase en la que se importa la url y se utiliza axios para hacer petisiones http

export class ContactsService {
    url = "http://localhost:9090/"

    list() {
        return axios.get(this.url + "contacts")
            .then(function (res) {
                let datos = res.data;
                let datosfinal = [];
                datos.forEach(element => {
                    if (element.dateDelete === null) {
                        datosfinal.push(element)
                    }
                });
                return datosfinal;
            })
    }

    create(contact) {
        console.log(contact);
        return axios.post(this.url + 'contact', contact)
            .then(res => res.data)
    }

    update(contact, id) {
        return axios.put(this.url + 'contact/' + id, contact)
            .then(res => res.data)
    }

    updateDelete(contact, id) {
        console.log(contact);
        console.log(id);
        return axios.patch(this.url + 'contact/dateDelete/' + id, contact)
            .then(res => res.data)
    }


}