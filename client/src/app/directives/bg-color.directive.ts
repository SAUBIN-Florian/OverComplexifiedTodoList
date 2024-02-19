import { Directive, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[bgColor]'
})
export class BgColorDirective {

  @Input()
  bgColor: string = "#E4E6C3";

  @HostListener("click", ["$event"])
  loadColor($event: Event) {
    console.log($event.target);
  }

}
