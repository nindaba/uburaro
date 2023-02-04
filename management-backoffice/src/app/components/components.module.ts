import {NgModule} from '@angular/core';
import {NavigationModule} from "./navigation/navigation.module";
import { LogoComponent } from './logo/logo.component';



@NgModule({
    declarations: [
    LogoComponent
  ],
    imports: [
        NavigationModule,
    ],
    exports: [
        NavigationModule,
        LogoComponent,
    ]
})
export class ComponentsModule {
}
