import {NgModule} from "@angular/core";
import {translatorConfig} from "./components/config/translator.config";
import {TranslateModule} from "@ngx-translate/core";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@NgModule({
    imports: [
        TranslateModule.forRoot(translatorConfig),
    ],
    exports:[
        TranslateModule,
        HttpClientModule,
        CommonModule,
    ]

})
export class AppCommonModule {

}