import {Injectable} from "@angular/core";

@Injectable({
    providedIn:"root"
})
export class BreadcrumbsService {
    facility:string = "default"
    pages: { page?: string; details?: string; } ={};
}