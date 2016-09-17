require 'net/http' # URI is required by Net::HTTP by default

module Jeyson
  class Client
    def initialize(root, default_headers = {})
      @root     = root
      @headers  = default_headers
      @remote   = Net::HTTP.new("http://localhost",5132)
    end

    def get(request_url, headers = {'Content-Type' => "application/json"})
      JSON.parse(Net::HTTP.get(url(request_url)))
    end

    def url(url)
      URI "#{@root}#{url}"
    end

    def put(request_url, body, headers = {'Content-Type' => "application/json"})
      @remote.send_request('PUT', request_url)
    end

    def url(url)
      URI "#{@root}#{url}"
    end

  end
end