require 'service_mock'

module Jeyson
  class TestHelper
    def path_to_jar
      "/Users/dawn/Documents/projects/wiremock/wiremock-standalone-2.1.12"
    end

    def root_dir
      File.expand "src/test/data/root-dir"
    end

    def port
      5132
    end

    def stub_root_url()
      "http://localhost:#{port}"
    end

    def start_server
      @server = ServiceMock::Server.new(path_to_jar)
      @server.start do |server|
        server.port             = port
        server.root_dir         = root_dir
        server.verbose          = true
        server.record_mappings  = false
        server.wait_for_process = true
        server.inherit_io       = false
      end
    end

    def stop_server
      @server.stop
    end
  end
end
