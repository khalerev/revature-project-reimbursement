import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  id:any;
  maxId:number = 0;
  tickets:any[] = [];

  constructor(private http:HttpClient) { }

  ngOnInit(): void {

    this.id = localStorage.getItem("userid");
    this.fetchTickets()
  }

  fetchTickets(){
    this.http.get('http://localhost:8090/api-servlet-app/tickets').subscribe({
      next: (data:any) => {
        this.tickets = data;
        this.tickets = this.tickets.filter(t => t.employeeId == this.id);
        this.tickets.forEach(t => {
          t.timestamp = new Date(t.timestamp).toString();
          if (this.maxId <= t.ticketId) {
            this.maxId = t.ticketId + 1;
          }
        });
      }
    })
  }

  onTitleChange(e:any){
    this.id = e.target.value
  }
  
  submitTicket(form:NgForm){
    console.log('adding menu..');
    let ticket:any = {
        employeeId:localStorage.getItem("userid"),
        amount: form.value.amount,
        description: form.value.description,
        type: form.value.type,
        ticketId: this.maxId,
        timestamp: null
    };
    this.http.post('http://localhost:8090/api-servlet-app/tickets',JSON.stringify(ticket)).subscribe({
      next: (data)=>{
        this.fetchTickets()
      }
    });
  }

}
