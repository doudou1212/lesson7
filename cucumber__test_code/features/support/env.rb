require 'capybara'
require 'site_prism'
require 'pry'

require 'require_all'
require_all 'lib'

Capybara.register_driver :selenium do |app|
  Capybara::Selenium::Driver.new(app, { browser: :firefox })
end

Capybara.default_driver = :selenium
Capybara.app_host = 'http://localhost:8080'