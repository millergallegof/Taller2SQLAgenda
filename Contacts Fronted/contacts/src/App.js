// import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react';
import { ContactsService } from './service/ContactsService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Fieldset } from 'primereact/fieldset';
import { Menubar } from 'primereact/menubar';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';


import 'primereact/resources/themes/nova/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';




export default class App extends Component {


  constructor() {
    let date = new Date();
    super();
    this.state = {
      visibleDialog: false,
      button: false,
      input: false,
      contact: {
        name: null,
        phone: null,
        email: null,
        dateBirth: null,
        dateCreate: date.toISOString().split('T')[0],
        dateUpdate: null,
        dateDelete: null
      },
      selectedContact: {
        contact: {
          dateCreate: date.toISOString().split('T')[0]
        }
      }
    };
    // creacion de los iconos del menu
    this.items = [
      {
        label: 'Crear Contacto',
        icon: 'pi pi-fw pi-plus',
        command: () => { this.showCreateDialog() }
      },
      {
        label: 'Editar Contacto',
        icon: 'pi pi-fw pi-pencil',
        command: () => { this.showUpdateDialog() }
      },
      {
        label: 'Eliminar Contacto',
        icon: 'pi pi-fw pi-trash',
        command: () => { this.disableInputs() }
      }
    ]
    this.contactsService = new ContactsService();
    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.updateDelete = this.updateDelete.bind(this);
    this.footer = (
      <div>
        <Button label="Guardar" icon="pi pi-check" onClick={this.create} />
      </div>
    )
    this.footer2 = (
      <div>
        <Button label="Modificar" icon="pi pi-check" onClick={this.update} />
      </div>
    )
    this.footer3 = (
      <div>
        <Button label="Eliminar" icon="pi pi-check" onClick={this.updateDelete} />
      </div>
    )

  }

  componentDidMount() {
    this.contactsService.list().then(data => this.setState({ contacts: data }))

  }

  updateDelete() {
    this.contactsService.updateDelete(this.state.contact, this.state.selectedContact.id).then(data => {
      this.setState({
        visibleDialog: false,
        button: false,
        input: false,
      });
      this.contactsService.list().then(data => this.setState({ contacts: data }));
    })
  }

  update() {
    this.contactsService.update(this.state.contact, this.state.selectedContact.id).then(data => {
      this.setState({
        visibleDialog: false,
        button: false,
        input: false,
      });
      this.contactsService.list().then(data => this.setState({ contacts: data }));
    })
  }

  create() {
    console.log(this.state.contact);
    this.contactsService.create(this.state.contact).then(data => {
      this.setState({
        visibleDialog: false,
        button: false,
        input: false,
        contact: {
          name: null,
          phone: null,
          email: null,
          dateBirth: null
        }
      });
      this.contactsService.list().then(data => this.setState({ contacts: data }));
    })

  }

  //devuelve al index.js los elementos html qe se van a mostrar en el index.html

  render() {
    var listContact = ['Name', 'Phone', 'Email', 'DateBirth']
    let valueFooter;
    if (this.state.button) {
      valueFooter = this.footer
    } else if (!this.state.button && !this.state.input) {
      valueFooter = this.footer2
    } else {
      valueFooter = this.footer3
    }
    return (
      <div style={{ width: '80%', margin: '50px', align: 'center' }}>
        <Menubar model={this.items}></Menubar>
        <br />
        <Fieldset legend="Agenda de Contactos">
          <DataTable value={this.state.contacts} selectionMode="single" selection={this.state.selectedContact} onSelectionChange={e => this.setState({ selectedContact: e.value })}>
            <Column field='id' header="ID"></Column>
            <Column field='name' header="Name"></Column>
            <Column field='phone' header="Phone"></Column>
            <Column field='email' header="Email"></Column>
            <Column field='dateBirth' header="Date Birth"></Column>
          </DataTable>
        </Fieldset>
        <Dialog header="Crear Contacto" visible={this.state.visibleDialog} style={{ width: '600px', height: '600px' }} footer={valueFooter} modal={true} onHide={() => this.setState({ visibleDialog: false })}>
          {listContact.map((dato, index) => {
            let campo = "";
            if (index === 0) {
              campo = this.state.contact.name
            } else if (index === 1) {
              campo = this.state.contact.phone
            } else if (index === 2) {
              campo = this.state.contact.email
            } else if (index === 3) {
              campo = this.state.contact.dateBirth
            }
            return (
              <div style={{ margin: '30px' }}>
                <span className="p-float-label">
                  <InputText value={campo} disabled={this.state.input} style={{ width: '100%', height: '100%' }} id={dato} onChange={(e) => {
                    let val = e.target.value
                    this.setState(prevState => {
                      let contact = Object.assign({}, prevState.contact)
                      if (index === 0) {
                        contact.name = val
                      } else if (index === 1) {
                        contact.phone = val
                      } else if (index === 2) {
                        contact.email = val
                      } else if (index === 3) {
                        contact.dateBirth = val
                      }
                      return { contact }
                    })
                  }} />

                  <label htmlFor={dato}>{dato}</label>

                </span>
                <br />
              </div>

            )
          })}

        </Dialog>
      </div>
    )
  }

  showCreateDialog() {
    this.setState({
      visibleDialog: true,
      button: true,
      input: false
      // contact: {
      //   name: null,
      //   phone: null,
      //   email: null,
      //   dateBirth: null,

      // }
    })
  }

  showUpdateDialog() {
    let date = new Date()
    this.setState({
      visibleDialog: true,
      button: false,
      input: false,
      contact: {
        id: this.state.selectedContact.id,
        name: this.state.selectedContact.name,
        phone: this.state.selectedContact.phone,
        email: this.state.selectedContact.email,
        dateBirth: this.state.selectedContact.dateBirth,
        dateCreate: this.state.selectedContact.dateBirth,
        dateUpdate: date.toISOString().split('T')[0],
        dateDelete: this.state.selectedContact.dateBirth
      }
    })
  }

  disableInputs() {
    let date = new Date()
    this.setState({
      visibleDialog: true,
      button: false,
      input: true,
      contact: {
        id: this.state.selectedContact.id,
        name: this.state.selectedContact.name,
        phone: this.state.selectedContact.phone,
        email: this.state.selectedContact.email,
        dateBirth: this.state.selectedContact.dateBirth,
        dateCreate: this.state.selectedContact.dateBirth,
        dateUpdate: date.toISOString().split('T')[0],
        dateDelete: this.state.selectedContact.dateBirth
      }
    })
  }
}

