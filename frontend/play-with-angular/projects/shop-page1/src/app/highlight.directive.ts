import { Directive, ElementRef, EventEmitter, HostListener, Input, Output } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {

  @Input() appHighlight: string = 'yellow';
  @Output() appHighlightChange= new EventEmitter<string>();

  constructor(private ele: ElementRef) { 
    // console.log('HighlightDirective initialized');
    // console.log(this.ele);
  }

  // ngOnInit() {
  //   // on Mouse Over
  //   this.ele.nativeElement.addEventListener('mouseover', () => {
  //     console.log('Mouse Over');
  //     this.ele.nativeElement.style.backgroundColor = 'yellow';
  //   });
    
  //   // on Mouse Out
  //   this.ele.nativeElement.addEventListener('mouseout', () => {
  //     console.log('Mouse Out');
  //     this.ele.nativeElement.style.backgroundColor = '';
  //   });
  // }


  @HostListener('mouseover') onMouseOver() {
    // console.log('Mouse Over');
    this.ele.nativeElement.style.backgroundColor = this.appHighlight;
    this.appHighlightChange.emit(this.appHighlight);
  }
  @HostListener('mouseout') onMouseOut() {
    // console.log('Mouse Out');
    this.ele.nativeElement.style.backgroundColor = '';
    this.appHighlightChange.emit('');
  }


}
