
class CategoryUtil {
    constructor(categoryEvent, secondaryCategoryEvent) {
        this.categoryEvent = categoryEvent;
        this.secondaryCategoryEvent = secondaryCategoryEvent;
    }


     #getOptionSelectedText() {
        return this.categoryEvent.options[primaryCategory.selectedIndex].text
    }

    removeChildNodesRemove(){
        while(secondaryCategory.hasChildNodes()){
            secondaryCategory.removeChild(secondaryCategory.firstElementChild);
        }
    }

     getSecondaryCategory() {
        fetch("/api/admin/category/" + this.#getOptionSelectedText())
            .then((response) => response.json())
            .then((data) => {
                data.forEach((a) => {
                    let optionElement = document.createElement("option");
                    optionElement.value = a.id;
                    optionElement.innerText = a.categoryName;
                    this.secondaryCategoryEvent.appendChild(optionElement);
                }); // forEach End
            });     // then End
    }
}