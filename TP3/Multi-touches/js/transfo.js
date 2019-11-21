System.register([], function (exports_1, context_1) {
    "use strict";
    var re_matrix, svg, idM, setMatrixCoordToElement, setMatrixToElement, getMatrixFromString, getPoint, getMatrixFromElement, drag, rotozoom;
    var __moduleName = context_1 && context_1.id;
    return {
        setters: [],
        execute: function () {
            re_matrix = /^matrix\((.*), (.*), (.*), (.*), (.*), (.*)\)$/;
            svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
            idM = svg.createSVGMatrix();
            idM.a = 1;
            idM.b = 0;
            idM.c = 0;
            idM.d = 1;
            idM.e = 0;
            idM.f = 0;
            //______________________________________________________________________________________________________________________
            exports_1("setMatrixCoordToElement", setMatrixCoordToElement = (element, a, b, c, d, e, f) => {
                element.style.transform = "matrix(" + a + "," + b + "," + c + "," + d + "," + e + "," + f + ")";
            });
            //______________________________________________________________________________________________________________________
            exports_1("setMatrixToElement", setMatrixToElement = (element, M) => {
                setMatrixCoordToElement(element, M.a, M.b, M.c, M.d, M.e, M.f);
            });
            //______________________________________________________________________________________________________________________
            exports_1("getMatrixFromString", getMatrixFromString = (str) => {
                let res = re_matrix.exec(str), matrix = svg.createSVGMatrix();
                matrix.a = parseFloat(res[1]) || 1;
                matrix.b = parseFloat(res[2]) || 0;
                matrix.c = parseFloat(res[3]) || 0;
                matrix.d = parseFloat(res[4]) || 1;
                matrix.e = parseFloat(res[5]) || 0;
                matrix.f = parseFloat(res[6]) || 0;
                return matrix;
            });
            //______________________________________________________________________________________________________________________
            exports_1("getPoint", getPoint = (x, y) => {
                let point = svg.createSVGPoint();
                point.x = x || 0;
                point.y = y || 0;
                return point;
            });
            //______________________________________________________________________________________________________________________
            exports_1("getMatrixFromElement", getMatrixFromElement = (element) => {
                return getMatrixFromString(window.getComputedStyle(element).transform || "matrix(1,1,1,1,1,1)");
            });
            //______________________________________________________________________________________________________________________
            exports_1("drag", drag = (element, originalMatrix, Pt_coord_element, Pt_coord_parent) => {
                let e = Pt_coord_parent.x - originalMatrix.a * Pt_coord_element.x - originalMatrix.c * Pt_coord_element.y;
                let f = Pt_coord_parent.y - originalMatrix.b * Pt_coord_element.x - originalMatrix.d * Pt_coord_element.y;
                setMatrixCoordToElement(element, originalMatrix.a, originalMatrix.b, originalMatrix.c, originalMatrix.d, e, f);
            });
            //______________________________________________________________________________________________________________________
            exports_1("rotozoom", rotozoom = (element, originalMatrix, Pt1_coord_element, Pt1_coord_parent, Pt2_coord_element, Pt2_coord_parent) => {
                let dx = Pt2_coord_element.x - Pt1_coord_element.x;
                let dy = Pt2_coord_element.y - Pt1_coord_element.y;
                let dx2 = Pt2_coord_parent.x - Pt1_coord_parent.x;
                let dy2 = Pt2_coord_parent.y - Pt1_coord_parent.y;
                let s, c;
                if (dx === 0 && dy !== 0) {
                    s = -dx2 / dy;
                    c = dy2 / dy;
                }
                else if (dx !== 0 && dy === 0) {
                    s = dy2 / dx;
                    c = dx2 / dx;
                }
                else if (dx !== 0 && dy !== 0) {
                    s = (dy2 / dy - dx2 / dx) / (dy / dx + dx / dy);
                    c = (dx2 + s * dy) / dx;
                }
                let e = Pt1_coord_parent.x - c * Pt1_coord_element.x + s * Pt1_coord_element.y;
                let f = Pt1_coord_parent.y - s * Pt1_coord_element.x - c * Pt1_coord_element.y;
                setMatrixCoordToElement(element, c, s, -s, c, e, f);
            });
        }
    };
});
//# sourceMappingURL=transfo.js.map