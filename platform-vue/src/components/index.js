import appShell from './packages/app-shell/index.js'
const version = '1.0.0'

const install = function (Vue, config = {}) {
    if (install.installed) return
    Vue.component(appShell.name, appShell)
};

export default {
    version,
    install
}