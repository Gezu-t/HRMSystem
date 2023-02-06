import { Component, ElementRef} from '@angular/core';
import { LayoutService } from './../service/app.layout.service';
@Component({
    selector: 'sidebar',
    templateUrl: './sidebar.component.html'
})
export class SidebarComponent {
    
    constructor(public layoutService: LayoutService, public el: ElementRef) { }
}

