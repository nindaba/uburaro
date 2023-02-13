import {Observable} from "rxjs";
import {Category} from "../../model/navigation.model";

export abstract class CategoryService {
    /**
     * Gets all the categories that belong to the selected facility which are active
     *
     * @return observable of categories
     */
    abstract getCategoriesByFacilityCode() : Observable<Category[]>;

    /**
     * Gets a category with {@code code}
     *
     * @param code
     * @return category
     */
    abstract getCategoryByCode(code: string): Observable<Category>;

}
