import { Component } from '@angular/core';
import { LayoutComponent } from './layout/layout.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LayoutComponent, HttpClientModule],
  templateUrl: './app.component.html',
  providers: [],
})
export class AppComponent {
  title = 'pixel-power-ng';
}
