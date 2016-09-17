require 'service_mock'
require 'json'
require './src/test/e2e/support/helper'
require './src/test/e2e/support/client'
require 'net/http'

RSpec.describe "jeyson-java" do

  describe "e2e" do

    before(:all) do
      @helper = Jeyson::TestHelper.new
      @client = Jeyson::Client.new(@helper.stub_root_url)
      # @server = ServiceMock::Server.new(@helper.path_to_jar)
      # @server.start do |server|
      #   server.port             = @helper.port
      #   server.root_dir         = @helper.root_dir
      #   server.verbose          = true
      #   server.record_mappings  = false
      #   server.wait_for_process = true
      #   server.inherit_io       = false
      # end
    end

    # after(:all) do
    #   @server.stop
    # end

    it "Should serve a plain json" do
      expected  = {"message" => "hello"}
      actual    = @client.get("/hello")
      expect(actual).to eq(expected)
    end

  end
end
