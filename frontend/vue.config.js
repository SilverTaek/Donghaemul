module.exports = {
  chainWebpack: (config) => {
    config.module
      .rule('eslint')
      .use('eslint-loader')
      .tap((options) => {
        options.fix = true; // auto-fix 옵션
        return options;
      });
    const svgRule = config.module.rule('svg');

    svgRule.uses.clear();

    svgRule
      .use('babel-loader')
      .loader('babel-loader')
      .end()
      .use('vue-svg-loader')
      .loader('vue-svg-loader');
  },
  transpileDependencies: ['vuetify'],
};
