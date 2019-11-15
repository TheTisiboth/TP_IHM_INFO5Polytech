System.register([], function (exports_1, context_1) {
    "use strict";
    var State, Transition, FSM;
    var __moduleName = context_1 && context_1.id;
    return {
        setters: [],
        execute: function () {
            State = class State {
                constructor() {
                    this.transitionsFrom = [];
                }
                //constructor() {}
                addTransitionFrom(t) {
                    this.transitionsFrom.push(t);
                }
                disable() {
                    this.transitionsFrom.forEach((t) => t.disable());
                    return this;
                }
                enable() {
                    this.transitionsFrom.forEach((t) => t.enable());
                    return this;
                }
            };
            Transition = class Transition {
                constructor(fsm, fromState, toState, eventTarget, eventNames, useCapture, action) {
                    this.fromState = fromState;
                    this.toState = toState;
                    this.eventTarget = eventTarget;
                    this.eventNames = eventNames;
                    this.useCapture = useCapture;
                    this.action = action;
                    this.fromState.addTransitionFrom(this);
                    this.cb = (evt) => {
                        if (this.action(evt, this)) {
                            fsm.setCurrentState(this.toState);
                        }
                    };
                }
                enable() {
                    this.eventTarget.forEach(es => {
                        this.eventNames.forEach(eventName => es.addEventListener(eventName, this.cb, this.useCapture));
                    });
                }
                disable() {
                    this.eventTarget.forEach(es => {
                        this.eventNames.forEach(eventName => es.removeEventListener(eventName, this.cb, this.useCapture));
                    });
                }
            };
            FSM = class FSM {
                //private states          : State[];
                //private transitions     : Transition[];
                setInitialState(initialState) {
                    this.initialState = initialState;
                }
                start() {
                    this.setCurrentState(this.initialState);
                }
                stop() {
                    this.setCurrentState(null);
                }
                setCurrentState(state) {
                    const changeState = this.currentState !== state;
                    if (this.currentState && changeState) {
                        this.currentState.disable();
                    }
                    this.currentState = state || this.currentState;
                    if (this.currentState && changeState) {
                        this.currentState.enable();
                    }
                    return this;
                }
                static parse(serializedFSM) {
                    let fsm = new FSM();
                    let states = new Map();
                    for (let t of serializedFSM.states) {
                        states.set(t, new State());
                    }
                    for (let t of serializedFSM.transitions) {
                        let eventNames;
                        if (t.eventName instanceof Array) {
                            eventNames = t.eventName;
                        }
                        else {
                            eventNames.push(t.eventName);
                        }
                        new Transition(fsm, states.get(t.from), states.get(t.to), t.eventTargets, eventNames, t.useCapture, t.action);
                    }
                    fsm.setInitialState(states.get(serializedFSM.initialState));
                    return fsm;
                }
            };
            exports_1("FSM", FSM);
        }
    };
});
//# sourceMappingURL=FSM.js.map