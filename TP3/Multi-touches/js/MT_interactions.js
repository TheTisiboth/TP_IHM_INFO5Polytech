System.register(["./FSM", "./transfo"], function (exports_1, context_1) {
    "use strict";
    var FSM_1, transfo, $;
    var __moduleName = context_1 && context_1.id;
    function multiTouch(element) {
        let pointerId_1, Pt1_coord_element, Pt1_coord_parent, pointerId_2, Pt2_coord_element, Pt2_coord_parent, originalMatrix, getRelevantDataFromEvent = (evt) => {
            for (let i = 0; i < evt.changedTouches.length; i++) {
                let touch = evt.changedTouches.item(i);
                if (touch.identifier === pointerId_1 || touch.identifier === pointerId_2) {
                    return touch;
                }
            }
            return null;
        };
        let MT_STATES;
        (function (MT_STATES) {
            MT_STATES[MT_STATES["Inactive"] = 0] = "Inactive";
            MT_STATES[MT_STATES["Translating"] = 1] = "Translating";
            MT_STATES[MT_STATES["Rotozooming"] = 2] = "Rotozooming";
        })(MT_STATES || (MT_STATES = {}));
        let fsm = FSM_1.FSM.parse({
            initialState: MT_STATES.Inactive,
            states: [MT_STATES.Inactive, MT_STATES.Translating, MT_STATES.Rotozooming],
            transitions: [
                {
                    from: MT_STATES.Inactive, to: MT_STATES.Translating,
                    eventTargets: [element],
                    eventName: ["touchstart"],
                    useCapture: false,
                    action: (evt) => {
                        evt.preventDefault();
                        pointerId_1 = evt.changedTouches.item(0).identifier;
                        let touch = getRelevantDataFromEvent(evt);
                        originalMatrix = transfo.getMatrixFromElement(element);
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        Pt1_coord_element = Pt1_coord_parent.matrixTransform(originalMatrix.inverse());
                        return true;
                    }
                },
                {
                    from: MT_STATES.Translating, to: MT_STATES.Translating,
                    eventTargets: [document],
                    eventName: ["touchmove"],
                    useCapture: true,
                    action: (evt) => {
                        evt.preventDefault();
                        evt.stopPropagation();
                        let touch = getRelevantDataFromEvent(evt);
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        transfo.drag(element, originalMatrix, Pt1_coord_element, Pt1_coord_parent);
                        return true;
                    }
                },
                {
                    from: MT_STATES.Translating,
                    to: MT_STATES.Inactive,
                    eventTargets: [document],
                    eventName: ["touchend"],
                    useCapture: true,
                    action: (evt) => {
                        evt.preventDefault();
                        pointerId_1 = null;
                        return true;
                    }
                },
                {
                    from: MT_STATES.Translating, to: MT_STATES.Rotozooming,
                    eventTargets: [element],
                    eventName: ["touchstart"],
                    useCapture: false,
                    action: (evt) => {
                        evt.preventDefault();
                        pointerId_2 = evt.changedTouches.item(0).identifier;
                        let touch = getRelevantDataFromEvent(evt);
                        Pt2_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        Pt2_coord_element = Pt2_coord_parent.matrixTransform(originalMatrix.inverse());
                        return true;
                    }
                },
                {
                    from: MT_STATES.Rotozooming, to: MT_STATES.Rotozooming,
                    eventTargets: [document],
                    eventName: ["touchmove"],
                    useCapture: true,
                    action: (evt) => {
                        evt.preventDefault();
                        evt.stopPropagation();
                        let touch = getRelevantDataFromEvent(evt);
                        if (touch.identifier === pointerId_1) {
                            Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        }
                        else if (touch.identifier === pointerId_2) {
                            Pt2_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        }
                        transfo.rotozoom(element, originalMatrix, Pt1_coord_element, Pt1_coord_parent, Pt2_coord_element, Pt2_coord_parent);
                        return true;
                    }
                },
                {
                    from: MT_STATES.Rotozooming,
                    to: MT_STATES.Translating,
                    eventTargets: [document],
                    eventName: ["touchend"],
                    useCapture: true,
                    action: (evt) => {
                        evt.preventDefault();
                        evt.stopPropagation();
                        pointerId_2 = null;
                        let touch = getRelevantDataFromEvent(evt);
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        Pt1_coord_element = Pt1_coord_parent.matrixTransform(originalMatrix.inverse());
                        return true;
                    }
                }
            ]
        });
        fsm.start();
    }
    //______________________________________________________________________________________________________________________
    //______________________________________________________________________________________________________________________
    //______________________________________________________________________________________________________________________
    function isString(s) {
        return typeof (s) === "string" || s instanceof String;
    }
    return {
        setters: [
            function (FSM_1_1) {
                FSM_1 = FSM_1_1;
            },
            function (transfo_1) {
                transfo = transfo_1;
            }
        ],
        execute: function () {
            exports_1("$", $ = (sel) => {
                let L = [];
                if (isString(sel)) {
                    L = Array.from(document.querySelectorAll(sel));
                }
                else if (sel instanceof Element) {
                    L.push(sel);
                }
                else if (sel instanceof Array) {
                    L = sel;
                }
                L.forEach(multiTouch);
            });
        }
    };
});
//# sourceMappingURL=MT_interactions.js.map