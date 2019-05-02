
/**************
 * 
 * A tiny JavaScript utility library built by Viet Anh -  For web development ONLY
 * Some ES6 syntax and features are used in this code
 * Created on Apr 9 2019 - To be constantly updated and extended
 * Current version: 1.1.0
 * 
 * UPDATE: Added jQuery support
 * 
 **************/

(function (global, $) {
    // All global object references here
    let document = global.document;

    // Initialize the 'valib' function
    // This is the primary DOM element selector of the library
    let valib = function (selector, ...positions) {
        return new valib.init(selector, ...positions);
    };

    // All standard DOM manipulation methods are here
    valib.prototype = {

        // Converts the vaObj to an array or an HTML collection of HTML elements
        toObject: function () {
            let selector = this.selector,
                positions = this.positions,
                result,
                posLength;

            posLength = positions.length;

            // exclude selector being undefined or null or ""
            if (!selector) {
                return selector === '' ? null : selector;
            }

            // exclude selector not being a string
            if (typeof selector !== 'string') {
                throw new Error('Invalid DOM selector');
            }

            if (selector === 'document') {
                return document;
            }

            if (selector.startsWith("#")) {
                // element selector by id
                let id = getDOMName(selector);
                return document.getElementById(id);
            }

            if (selector.startsWith(".")) {
                // class selector
                let className = getDOMName(selector);
                result = document.getElementsByClassName(className);
            } else {
                // tag selector
                result = document.getElementsByTagName(selector);
            }

            if (result.length > 0 && posLength > 0) {
                if (posLength === 1) {
                    let position, index;
                    position = positions[0];
                    index = getIndexByPosition(result, position);
                    result = result[index];
                } else {
                    let temp = map(positions, position => {
                        let index = getIndexByPosition(result, position);
                        return result[index];
                    });

                    result = temp;
                }
            }

            return result;
        },

        isArrayLike: function () {
            return isArrayLike(this.object);
        },

        // Gets the HTML element at specific position from the selected HTML elements
        at: function (position) {
            let elements, index;
            elements = this.object;
            index = getIndexByPosition(elements, position);
            return elements[index];
        },

        // Sets click event handler(s) for the selected HTML element(s)
        click: function (callback) {
            let elements = this.object;
            if (elements) {
                if (elements === document || elements instanceof HTMLElement) {
                    setClick(elements, callback);
                } else if (isArrayLike(elements) && elements.length > 0) {
                    each(elements, element => setClick(element, callback));
                }
            }
            return this;
        },

        // Sets keypress event handler(s) for specific key for the selected HTML element(s)
        keypress: function (keyCode, callback, preventDefault = false) {
            let elements = this.object;
            if (elements) {
                if (elements === document || elements instanceof HTMLElement) {
                    setKeypress(elements, keyCode, callback, preventDefault);
                } else if (isArrayLike(elements) && elements.length > 0) {
                    each(elements, element => setKeypress(element, keyCode, callback, preventDefault));
                }
            }
            return this;
        },

        // No arguments: Returns the inner HTML of the selected HTML element(s)
        // 1 argument: Sets the inner HTML of the selected HTML element(s)
        html: function (html) {
            let elements = this.object;
            if (html && elements) {
                if (elements instanceof HTMLElement) {
                    elements.innerHTML = html;
                } else if (isArrayLike(elements) && elements.length > 0) {
                    each(elements, element => element.innerHTML = html);
                }
                return this;
            }
            return elements instanceof HTMLElement ? elements.innerHTML : null;
        },

        // No arguments: Returns the inner text of the selected HTML element(s)
        // 1 argument: Sets the inner text of the selected HTML element(s)
        text: function (text) {
            let elements = this.object;

            if (elements) {
                if (elements instanceof HTMLElement) {
                    elements.innerText = text;
                } else if (isArrayLike(elements) && elements.length > 0) {
                    each(elements, element => element.innerText = text);
                }
                return this;
            }

            return elements instanceof HTMLElement ? elements.innerText : null;
        },

        // Returns the class name(s) of the selected HTML element(s)
        class: function () {
            let elements = this.object;
            return elements instanceof HTMLElement ? elements.className : null;
        },

        // Add class(es) to the selected HTML element(s)
        addClass: function (...classNames) {
            return modifyClass(this, classNames, 'add');
        },

        // Remove class(es) from the selected HTML element(s)
        removeClass: function (...classNames) {
            return modifyClass(this, classNames, 'remove');
        },

        // Toggle the class(es) of the selected HTML element(s)
        toggleClass: function (...classNames) {
            return modifyClass(this, classNames, 'toggle');
        },

        // Inserts HTML to the end of the current inner HTML of the selected HTML element(s)
        insertLeadingHTML: function (html) {
            insertHTML(this.object, 'afterbegin', html);
            return this;
        },

        // Inserts HTML to the beginning of the current inner HTML of the selected HTML element(s)
        insertEndingHTML: function (html) {
            insertHTML(this.object, 'beforeend', html);
            return this;
        }
    };

    // Initialize the "initializer" function of the library
    // This is the primary constructor function of the library
    valib.init = function (selector = '', ...positions) {
        this.selector = selector;
        this.positions = positions;
        this.object = this.toObject();
    };

    valib.init.prototype = valib.prototype;


    // Current version
    valib.version = '1.0.5';


    /* OBJECT METHODS */

    // Check valid object
    valib.isObject = function (obj) {
        var type = typeof obj;
        return type === 'function' || type === 'object' && !!obj;
    };

    // Check empty object
    valib.isEmptyObject = function (obj) {
        let name;

        for (name in obj) {
            return false;
        }

        return true;
    };

    // Check if an object is a function
    let isFunction = valib.isFunction = function (obj) {
        return typeof obj === "function" && typeof obj.nodeType !== "number";
    };

    let toType = valib.type = function (obj) {
        if ($) {
            return $.type(obj);
        }

        throw new Error('jQuery not successfully loaded');
    };

    // Returns an array of keys extracted from an object
    valib.getAllKeys = function (obj) {
        if (!valib.isObject(obj)) return [];

        var keys = [];
        for (var key in obj) keys.push(key);

        return keys;
    };

    // Extends an object with an array of prototypal objects
    valib.extend = function (target, ...sources) {
        if (sources.length < 1 || target == null) return target;

        each(sources, source => {
            const keys = valib.getAllKeys(source);
            each(keys, key => {
                if (target[key] === void 0) target[key] = source[key];
            });
        });

        return target;
    };

    /* ARRAY METHODS */

    // Check if an object is array-like
    let isArrayLike = valib.isArrayLike = function (obj) {
        var length = !!obj && "length" in obj && obj.length,
            type = toType(obj);

        if (isFunction(obj) || obj === global) {
            return false;
        }

        return type === "array" || length === 0 ||
            typeof length === "number" && length > 0 && (length - 1) in obj;
    };

    // Similar to Array.prototype.map
    // Usable for not only instances of Array but also array-like objects
    let map = valib.map = function (array, callback) {
        let newArray = [];

        for (let index = 0; index < array.length; index++) {
            const element = array[index];
            const newElement = callback(element, index, array);
            newArray.push(newElement);
        }

        return newArray;
    };

    // Similar to Array.prototype.filter
    // Usable for not only instances of Array but also array-like objects
    let filter = valib.filter = function (array, predicate) {
        let newArray = [];

        for (let index = 0; index < array.length; index++) {
            const element = array[index];
            if (predicate(element)) {
                newArray.push(element);
            }
        }

        return newArray;
    };

    // Similar to Array.prototype.forEach
    // Usable for not only instances of Array but also array-like objects
    let each = valib.each = function (array, callback) {
        for (let index = 0; index < array.length; index++) {
            const element = array[index];
            callback(element, index, array);
        }
    };

    /* DOM MANIPULATION EXTENDED: METHODS & UTILITIY FUNCTIONS */

    let getIndexByPosition = function (array, position) {
        let length = array.length,
            type = typeof position;

        if (length > 0) {
            if (type === 'number') {
                return position;
            }

            if (type === 'string' && position) {
                let index = -1;

                if (position === 'first') {
                    index = 0;
                } else if (position === 'middle' || position === 'mid') {
                    let middle = Math.floor(length / 2);
                    index = length % 2 === 0 ? middle - 1 : middle;
                } else if (position === 'last') {
                    index = length - 1;
                }

                return index;
            }
        }

        return -1;
    };

    let getDOMName = function (selector) {
        if (selector.startsWith("#") || selector.startsWith(".")) {
            return selector.substring(1);
        }
        return selector;
    };

    let setClick = function (HTMLElement, callback) {
        HTMLElement.addEventListener('click', function (event) {
            callback(event);
        });
    };

    let setKeypress = function (HTMLElement, keyCode, callback, preventDefault) {
        HTMLElement.addEventListener('keypress', function (event) {
            let key;

            if (typeof keyCode === 'number') {
                key = event.which || event.keyCode;
            } else if (typeof keyCode === 'string') {
                key = event.key;
            }

            if (key === keyCode) {
                if (preventDefault) {
                    event.preventDefault();
                }
                callback(event);
            }
        });
    };

    // insert inner HTML
    let insertHTML = function (elements, insertPosition, html) {
        if (elements && html) {
            if (elements instanceof HTMLElement) {
                elements.insertAdjacentHTML(insertPosition, html);
            } else if (isArrayLike(elements) && elements.length > 0) {
                each(elements, element => element.insertAdjacentHTML(insertPosition, html));
            }
        }
    };

    let modifyClass = function (vaObj, classNames, operation) {
        let elements = vaObj.object;

        if (classNames.length > 0) {
            if (elements instanceof HTMLElement) {
                modifyClassList(elements, classNames, operation);
            } else if (isArrayLike(elements) && elements.length > 0) {
                each(elements, element => modifyClassList(element, classNames, operation));
            } else {
                throw new Error('Unexpected type: Instance of HTMLElement or HTMLCollection or Array required');
            }
        }

        return vaObj;
    };

    let modifyClassList = function (element, classNames, operation) {
        switch (operation) {
            case 'add':
                each(classNames, className => element.classList.add(className));
                break;
            case 'remove':
                each(classNames, className => element.classList.remove(className));
                break;
            case 'toggle':
                each(classNames, className => element.classList.toggle(className));
                break;
        }
    };

    // Set event handler for the "DOM-ready" event
    valib.ready = function (callback) {
        document.addEventListener('DOMContentLoaded', callback);
    };

    /* AJAX */

    /* The 'ajaxGET' method, which takes 2 arguments, sends a HTTP GET request to a server when called.
    url: URL of the API from which you want to get data
    onReceive: how you want to do with the retrieved data
    */
    valib.ajaxGET = function (url, onReceive) {
        if (url) {
            let request = new XMLHttpRequest();

            request.open('GET', url, true);
            request.send();
            request.onload = function () {
                let jsonText, obj;
                jsonText = this.responseText;

                if (jsonText) {
                    try {
                        obj = JSON.parse(jsonText);
                    } catch (error) {
                        console.log(error);
                        obj = {};
                    }
                    onReceive(obj, jsonText);
                }
            };
        }
    };

    /* The 'ajaxPOST' method requires a set of specifications to send a HTTP POST request to a server.
    Put all specifications into one object (called 'specs') and pass it as the only parameter to the method.
    The 'specs' object should look like this:
    {
        url: '/url where you want to submit your data',
        requestHeader: {
            name: 'request name',
            value: 'request value'
        },
        data: 'data you want to submit to server',
        onStateChange: function (responseText) {
            // what to do when the request is successful
        }
    }
    */
    valib.ajaxPOST = function (specs) {
        let url, requestHeader, data, onStateChange, request;
        url = specs.url;
        requestHeader = specs.requestHeader;
        data = specs.data;
        onStateChange = specs.onStateChange;
        request = new XMLHttpRequest();

        request.open('POST', url, true);
        request.setRequestHeader(requestHeader.name, requestHeader.value);
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                onStateChange(request.responseText);
            } else {
                throw new Error('Unsuccessful HTTP POST request');
            }
        };
        request.send(data);
    };

    // Gets URL parameters assigned to the current page address
    valib.getCurrentUrlParams = function () {
        let queryString = global.location.search;

        if (queryString) {
            return new URLSearchParams(queryString);
        }

        return null;
    };

    // Gets value with specific key from parameters assigned to the current page address
    valib.getValueFromCurrentUrl = function (key) {
        let urlParams = valib.getCurrentUrlParams();

        if (urlParams) {
            urlParams.get(key);
        }

        return null;
    };


    // Expose 'valib' as two aliases 'window.valib' and 'window._va' to global context
    global.valib = global._va = valib;

})(window, jQuery ? jQuery : null);