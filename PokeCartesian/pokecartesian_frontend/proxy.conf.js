const PROXY_CONFIG = [
  {
    context: ['/apiPokemon'],
    target: 'http://localhost:9090/pokemon',
    secure: false,
    logLevel: 'debug',
    pathRewrite: { '^/apiPokemon': ''}
  },
  {
    context: ['/apiArea'],
    target: 'http://localhost:9090/area',
    secure: false,
    logLevel: 'debug',
    pathRewrite: { '^/apiArea': ''}
  }
]

module.exports = PROXY_CONFIG;
