module.exports = {
  env: {
    browser: true,
    es6: true,
  },
  extends: ['plugin:vue/essential', 'google', 'prettier', 'plugin:prettier/recommended'],
  globals: {
    Atomics: 'readonly',
    SharedArrayBuffer: 'readonly',
  },
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: 'module',
  },
  plugins: ['vue', 'prettier'],
  rules: {
    'prettier/prettier': [
      'error',
      {
        singleQuote: true,
        semi: true,
        tabWidth: 2,
        trailingComma: 'es5',
        printWidth: 100,
        endOfLine: 'lf',
        arrowParens: 'always',
      },
    ],
  },
};
