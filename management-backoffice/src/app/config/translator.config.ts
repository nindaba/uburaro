import {TranslateLoader} from "@ngx-translate/core";
import {HttpClient} from "@angular/common/http";
import {TranslateModuleConfig} from "@ngx-translate/core/public_api";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

export function httpLoaderFactory(http: HttpClient){
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
export const translatorConfig: TranslateModuleConfig = {
    defaultLanguage: "fr",
    useDefaultLang: true,
    loader: {
        provide: TranslateLoader,
        useFactory: httpLoaderFactory,
        deps: [HttpClient]
    }
}