import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-voting-item',
  imports: [],
  templateUrl: './voting-item.component.html',
  styleUrl: './voting-item.component.css'
})
export class VotingItemComponent {

  @Input("value")
  item: any;

  @Input("votingLines")
  votingLines: any[] = [];

  @Output("onVote")
  onVote: EventEmitter<any> = new EventEmitter(); // Observeable stream

  handleVote(type: string) {
    let event = {
      item: this.item,
      type: type
    }
    this.onVote.emit(event);
  }


}
