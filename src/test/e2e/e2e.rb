require 'service_mock'
require 'json'
require './src/test/e2e/helper'

RSpec.describe "jeyson-java" do

  describe "e2e" do

    before(:all) do
      @server = ServiceMock::Server.new('standalone-2.0.10-beta')
    end

    it "Setup" do
      expect("one").to eq("ones")
    end
  end
end
