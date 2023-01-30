import { Component, OnInit } from '@angular/core';

export interface MenuItem {
  name?: string;
  icon?: string;
  link?: string;
  title?: boolean;
  divider?: boolean;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuList: MenuItem[] = [
    {
      name: 'Title',
      title: true,
    },
    {
      name: 'Components',
      link: '/',
      icon: 'folder'
    },
    {
      name: 'Components',
      link: '/dashboard',
      icon: 'folder'
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
