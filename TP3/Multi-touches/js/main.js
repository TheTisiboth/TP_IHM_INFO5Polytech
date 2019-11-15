System.register(["./MT_interactions"], function (exports_1, context_1) {
    "use strict";
    var MT_interactions_1, PromesseDocumentPret;
    var __moduleName = context_1 && context_1.id;
    return {
        setters: [
            function (MT_interactions_1_1) {
                MT_interactions_1 = MT_interactions_1_1;
            }
        ],
        execute: function () {
            PromesseDocumentPret = new Promise((resolve) => {
                if (document.readyState === "complete") {
                    resolve();
                }
                else {
                    document.onreadystatechange = () => document.readyState === "complete" ? resolve() : null;
                }
            });
            PromesseDocumentPret.then(() => {
                MT_interactions_1.$(".multiTouchCopntainer img, .multiTouchCopntainer video");
            });
        }
    };
});
//# sourceMappingURL=main.js.map