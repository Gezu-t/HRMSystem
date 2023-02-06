import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from '../service/app.layout.service';


@Component({
    selector: 'header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('headermenubutton') headerMenuButton!: ElementRef;

    @ViewChild('headermenu') menu!: ElementRef;

    constructor(public layoutService: LayoutService) { }
}
