import {NgModule} from "@angular/core";
import {translatorConfig} from "./config/translator.config";
import {TranslateModule} from "@ngx-translate/core";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [
        TranslateModule.forRoot(translatorConfig),
        RouterModule
    ],
    exports:[
        TranslateModule,
        HttpClientModule,
        CommonModule,
        RouterModule
    ]

})
export class AppCommonModule {

}