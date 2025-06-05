import { Component } from '@angular/core';
import { VotingTableComponent } from '../voting-table/voting-table.component';
import { VotingItemComponent } from '../voting-item/voting-item.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-voting-box',
  imports: [
    CommonModule,
    VotingTableComponent,
    VotingItemComponent
  ],
  templateUrl: './voting-box.component.html',
  styleUrl: './voting-box.component.css'
})
export class VotingBoxComponent {

  // state
  votingItems = [
    { id: 1, name: 'react.js'},
    { id: 2, name: 'angular' },
    { id: 3, name: 'vue.js' },
    { id: 4, name: 'svelte' },
    { id: 5, name: 'ember.js' },
    { id: 6, name: 'backbone.js' },
  ];
  votingLines = [
    { id: 1, name: 'react.js', likes: 10,dislikes: 0 },
    { id: 2, name: 'angular', likes: 20,dislikes: 0 },
    { id: 3, name: 'vue.js', likes: 5,dislikes: 0 }
  ];

  // state management
  handleVote(vote: {item: {id: number, name: string}, type: 'up' | 'down'}) {
    let {item,type} = vote;
    let votingLine = this.votingLines.find(line => line.id === item.id);
    if (!votingLine) {
      votingLine = { ...item, likes: 0, dislikes: 0 };
      this.votingLines.push(votingLine);
    }
    if (type === 'up') {
      votingLine.likes++;
    } else if (type === 'down') {
      votingLine.dislikes++;
    }
  }

}
