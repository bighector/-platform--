import appShell from './packages/app-shell/index.js'
import indexFloor from './packages/index-floor/index.js'
const version = '1.0.0'

const install = function (Vue, config = {}) {
    if (install.installed) return
    Vue.component(appShell.name, appShell)
    Vue.component(indexFloor.name, indexFloor)
};

export default {
    version,
    install
}