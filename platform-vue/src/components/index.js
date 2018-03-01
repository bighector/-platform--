import appShell from './packages/app-shell/index.js'
import indexFloor from './packages/index-floor/index.js'
import categoryBox from './packages/category-box/index.js'
import cartCell from './packages/cart-cell/index.js'
import number from './packages/number/index.js'
import selector from './packages/selector/index.js'
import specialCell from './packages/special-cell/index.js'
import goodsBox from './packages/goods-box/index.js'

const version = '1.0.0'

const install = function (Vue, config = {}) {
    if (install.installed) return
    Vue.component(appShell.name, appShell)
    Vue.component(indexFloor.name, indexFloor)
    Vue.component(categoryBox.name, categoryBox)
    Vue.component(cartCell.name, cartCell)
    Vue.component(number.name, number)
    Vue.component(selector.name, selector)
    Vue.component(specialCell.name, specialCell)
    Vue.component(goodsBox.name, goodsBox)
};

export default {
    version,
    install
}