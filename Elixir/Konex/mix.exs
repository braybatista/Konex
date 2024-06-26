defmodule ReactiveCommons.MixProject do
  use Mix.Project

  @version "2.0.12"

  def project do
    [
      app: :konex_test_app,
      version: @version,
      elixir: "~> 1.13",
      deps: deps(),
      description: description(),
      package: package(),
      source_url: "https://github.com/braybatista/Konex",
      xref: [exclude: [:crypto]],
      name: "konex_test_app",
    ]
  end

  defp description() do
    "Konex app hello world."
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:decimal, "~> 1.0"},
      {:ex_doc, "~> 0.14", only: :dev, runtime: false}
    ]
  end

  defp package() do
    [
      # This option is only needed when you don't want to use the OTP application name
      # name: "konex_test_app",
      # The organization the package belongs to. The package will be published to the organization repository, defaults = i"hexpm" repository.
      # organization: "bancolombia",
      files: ["mix.exs", ".formatter.exs"],
      maintainers: ["Brayan Batista Zuniga"],
      licenses: ["Apache-2.0"],
      links: %{"GitHub" => "https://github.com/braybatista/Konex"}
    ]
  end
end
