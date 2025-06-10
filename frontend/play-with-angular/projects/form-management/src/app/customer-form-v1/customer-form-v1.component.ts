import { JsonPipe, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer-form-v1',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    JsonPipe,
    NgIf
  ],
  templateUrl: './customer-form-v1.component.html',
  styleUrl: './customer-form-v1.component.css'
})
export class CustomerFormV1Component {

  customerModel: any={ };

  handleSubmit(event: Event, customerForm: NgForm) { 
    event.preventDefault();
    console.log('Form submitted:', this.customerModel);
    console.log('Form submitted:', customerForm.value);
  }

  loadCustomer() { 
    const customer = {
      firstName: 'John',
      lastName: 'Doe',
    }
    this.customerModel = customer;
    console.log('Customer loaded:', this.customerModel);
  }

}
